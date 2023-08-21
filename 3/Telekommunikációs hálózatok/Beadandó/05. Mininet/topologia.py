#!/usr/bin/python

from mininet.net import Mininet
from mininet.node import Controller, RemoteController, OVSController
from mininet.node import CPULimitedHost, Host, Node
from mininet.nodelib import LinuxBridge #OVSKernelSwitch, UserSwitch
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
    r7 = net.addHost('r7', cls=Node, ip='0.0.0.0')
    r7.cmd('sysctl -w net.ipv4.ip_forward=1')
    s2 = net.addSwitch('s2', cls=LinuxBridge, failMode='standalone', stp=1)
    s4 = net.addSwitch('s4', cls=LinuxBridge, failMode='standalone', stp=1)
    r8 = net.addHost('r8', cls=Node, ip='0.0.0.0')
    r8.cmd('sysctl -w net.ipv4.ip_forward=1')
    s1 = net.addSwitch('s1', cls=LinuxBridge, failMode='standalone', stp=1)
    r6 = net.addHost('r6', cls=Node, ip='0.0.0.0')
    r6.cmd('sysctl -w net.ipv4.ip_forward=1')
    s3 = net.addSwitch('s3', cls=LinuxBridge, failMode='standalone', stp=1)
    s5 = net.addSwitch('s5', cls=LinuxBridge, failMode='standalone', stp=1)

    info( '*** Add hosts\n')
    h5 = net.addHost('h5', cls=Host, ip='35.0.0.1', defaultRoute=None)
    h6 = net.addHost('h6', cls=Host, ip='45.0.0.1', defaultRoute=None)
    h1 = net.addHost('h1', cls=Host, ip='25.0.0.1', defaultRoute=None)
    h2 = net.addHost('h2', cls=Host, ip='25.0.0.2', defaultRoute=None)
    h3 = net.addHost('h3', cls=Host, ip='25.0.0.3', defaultRoute=None)
    h4 = net.addHost('h4', cls=Host, ip='55.0.0.1', defaultRoute=None)

    info( '*** Add links\n')
    net.addLink(h1, s1)
    net.addLink(h2, s2)
    net.addLink(h3, s3)
    net.addLink(s1, s4)
    net.addLink(s2, s4)
    net.addLink(s3, s5)
    net.addLink(s2, s5)
    net.addLink(s4, s5)
    net.addLink(s4, r6)
    net.addLink(s5, r7)
    net.addLink(r6, r8)
    net.addLink(r7, r8)
    net.addLink(h5, r6)
    net.addLink(h6, r7)
    net.addLink(h4, r8)

    info( '*** Starting network\n')
    net.build()
    info( '*** Starting controllers\n')
    for controller in net.controllers:
        controller.start()

    info( '*** Starting switches\n')
    net.get('s2').start([])
    net.get('s4').start([])
    net.get('s1').start([])
    net.get('s3').start([])
    net.get('s5').start([])

    info( '*** Post configure switches and hosts\n')

    CLI(net)
    net.stop()

if __name__ == '__main__':
    setLogLevel( 'info' )
    myNetwork()

