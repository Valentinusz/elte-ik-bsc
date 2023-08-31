import json
import sys


def find_path_to_allocate(start, end, links, circuits, demand):
    for circuit in circuits:
        if circuit[0] == start and circuit[-1] == end:
            links_in_path = [[circuit[i], circuit[i+1]] for i in range(len(circuit)-1)]
            if check_path_capacity(links_in_path, links, demand):
                return links_in_path, links, demand


def check_path_capacity(links_in_path, links, demand):
    success = True
    for link_in_path in links_in_path:
        for link in links:
            if link['points'] == link_in_path:
                if link['capacity'] < demand:
                    success = False
    if success:
        return True


def allocate_resources(links_in_path, links, demand):
    affected_links = []
    for link_in_path in links_in_path:
        for i in range(0, len(links)):
            link = links[i]
            if link['points'] == link_in_path:
                link['capacity'] -= demand
                affected_links.append(i)
    return affected_links


def free_resources(links, affected_links, demand):
    for link in affected_links:
        links[link]['capacity'] += demand


def main():
    try:
        with open(sys.argv[1], 'r') as file:
            data = json.load(file)
            event_number = 0
            duration = data['simulation']['duration']  # duration of the simulation
            allocated_resources = {}
            successful_demands = []
            for time in range(1, duration+1):
                for demand in data['simulation']['demands']:
                    if demand['start-time'] == time:
                        start = demand['end-points'][0]
                        end = demand['end-points'][1]

                        result = find_path_to_allocate(start, end, data['links'], data['possible-circuits'], demand['demand'])
                        success = False
                        if result:
                            success = True
                            allocated = allocate_resources(*result)
                            allocated_resources[demand['end-time']] = allocated
                            successful_demands.append(demand)
                        event_number += 1
                        print(f'{event_number}. igény foglalás: {start}<->{end} st:{time} – {"sikeres" if success else "sikertelen"}')
                    elif demand['end-time'] == time and demand in successful_demands:
                        start = demand['end-points'][0]
                        end = demand['end-points'][1]

                        free_resources(data['links'], allocated_resources[time], demand['demand'])

                        event_number += 1
                        print(f'{event_number}. igény felszabadítás: {start}<->{end} st:{time}')
            '''
            end_points = data['end-points']
            switches = data['switches']
            links = data['links']
            possible_circuits = data['possible-circuits']
            print(end_points)
            print(switches)
            for i in links:
                print(i)
            for i in possible_circuits:
                print(i)

            link_points = {link['points']: link['capacity'] for link in data['links']}
            print(link_points)

            duration = data['simulation']['duration']
            print(duration)

            event_number = 1
            for time in range(1, duration+1):
                for demand in data['simulation']['demands']:
                    start_point = demand['end-points'][0]
                    end_point = demand['end-points'][1]
                    if time == demand['start-time']:
                        success = False
                        for circuit in data['possible-circuits']:
                            if circuit['points'][0] == start_point and circuit['points'][-1] == end_point:
                                #or i in range(1,len(circuit)-1):
                                    #if circuit[0:i] in link_points and
                                success = True
                                circuit['capacity'] -= demand['demand']
                                break
                        print(f'{event_number}. igény foglalás: {start_point}<->{end_point} st:{time} – {"sikeres" if success else "sikertelen"}')
                        event_number += 1
                    elif time == demand['end-time']:
                        for circuit in data['possible-circuits']:
                            if circuit['points'][0] == start_point and circuit['points'][-1] == end_point:
                                circuit['capacity'] += demand['demand']
                                break
                        print(f'{event_number}. igény felszabadítás: {start_point}<->{end_point} st:{time}')
                        event_number += 1
                        '''
    except IndexError:
        print(f"Too few command-line arguments supplied. (expected: 1, found: {len(sys.argv)-1})", file=sys.stderr)
    except FileNotFoundError as e:
        print(e)

if __name__ == "__main__":
    main()

