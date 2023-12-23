from random import randint
from typing import Generator


def generate_ip() -> Generator[str, None, None]:
    """
    Ip address generator.

    Returns
    -------
    Generator
        A generator that creates unique ip addresses.
    """

    generated = []

    while True:
        address = f'{".".join([str(randint(0, 255)) for _ in range(4)])}:{randint(0, 65535)}'
        if address not in generated:
            yield address
        else:
            continue
