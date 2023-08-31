import sys
import struct
if len(sys.argv) >= 4:
    encoding_format = '9sif?c10s'
    unpacker = struct.Struct(encoding_format)
    with open(sys.argv[1], 'rb') as file:
        file.seek(int(sys.argv[2])*unpacker.size)
        line = file.read(struct.calcsize(encoding_format))
        unpacked_data = unpacker.unpack(line)

        converted_data = []
        for data in unpacked_data:
            if isinstance(data, (bytes, bytearray)):
                converted_data.append(str(data, "utf-8"))
            else:
                converted_data.append(data)

        data_to_print = ''
        for i in range(3, len(sys.argv)):
            data_to_print += str(converted_data[int(sys.argv[i])])
            data_to_print += ' '
        print(data_to_print.strip())
