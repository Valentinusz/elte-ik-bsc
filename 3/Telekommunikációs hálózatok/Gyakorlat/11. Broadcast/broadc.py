#!/usr/bin/python

from mininet.net import Mininet
from mininet.node import Controller, RemoteController, OVSController
from mininet.node import CPULimitedHost, Host, Node
from mininet.nodelib import LinuxBridge
from mininet.node import OVSKernelSwitch, UserSwitch
from mininet.node import IVSSwitch
from mininet.cli import CLI
from mininet.log import setLogLevel, info
from mininet.link import TCLink, Intf
from subprocess import call

def myNetwork():

    net = Mininet( topo=None,
                   build=False,
                   ipBase='10.0.0.0/8')

    info( '*** Adding controller\n' )
    info( '*** Add switches\n')
    r1 = net.addHost('r1', cls=Node, ip='0.0.0.0')
    r1.cmd('sysctl -w net.ipv4.ip_forward=1')
    r2 = net.addHost('r2', cls=Node, ip='0.0.0.0')
    r2.cmd('sysctl -w net.ipv4.ip_forward=1')
    s4 = net.addSwitch('s4', cls=LinuxBridge, failMode='standalone')
    s3 = net.addSwitch('s3', cls=LinuxBridge, failMode='standalone')

    info( '*** Add hosts\n')
    h2 = net.addHost('h2', cls=Host, ip='10.0.1.2/24', defaultRoute=None)
    h4 = net.addHost('h4', cls=Host, ip='192.168.100.2/24', defaultRoute=None)
    h3 = net.addHost('h3', cls=Host, ip='192.168.100.1/24', defaultRoute=None)
    h1 = net.addHost('h1', cls=Host, ip='10.0.1.1/24', defaultRoute=None)

    info( '*** Add links\n')
    net.addLink(r2, r1)
    net.addLink(s3, r1)
    net.addLink(h1, s3)
    net.addLink(s3, h2)
    net.addLink(r2, s4)
    net.addLink(s4, h3)
    net.addLink(s4, h4)

    info( '*** Starting network\n')
    net.build()
    info( '*** Starting controllers\n')
    for controller in net.controllers:
        controller.start()

    info( '*** Starting switches\n')
    net.get('s4').start([])
    net.get('s3').start([])

    info( '*** Post configure switches and hosts\n')

    CLI(net)
    net.stop()

if __name__ == '__main__':
    setLogLevel( 'info' )
    myNetwork()

