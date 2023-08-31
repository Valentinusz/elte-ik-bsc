import sys
import struct


def task1():
    file_names = sys.argv[1:5]
    formats = ['9s i f', 'f ? c', 'c i 9s', 'f 9s ?']
    for (file_name, file_data_format) in zip(file_names, formats):
        try:
            with open(file_name, 'rb') as file:
                unpacker = struct.Struct(file_data_format)
                data = file.read(unpacker.size)
                print(unpacker.unpack(data))
        except FileNotFoundError:
            print(f'{file_name} not found.', file=sys.stderr)


def task2():
    print(struct.pack('10s i ?', b'elso', 68,         True))
    print(struct.pack('f ? c',   71.5,    False,      b'X'))
    print(struct.pack('i 8s f',  59,      b'masodik', 78.9))
    print(struct.pack('c i 11s', b'Z',    90,         b'harmadik'))


def main():
    task1()
    task2()


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    main()
