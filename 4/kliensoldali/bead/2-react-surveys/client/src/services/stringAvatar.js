import {stringToColor} from './stringToColor.js';

export function stringAvatar(name) {
    const split = name.split(' ');

    const children = split.length > 1 ? `${split[0][0]}${split[1][0]}` : name[0];

    return {
        sx: {
            bgcolor: stringToColor(name),
            width: 96,
            height: 96
        },
        children,
    };
}
