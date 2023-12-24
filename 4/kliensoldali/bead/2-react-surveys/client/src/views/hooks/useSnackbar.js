import {useState} from 'react';

/**
 * Custom hook for controlling the state of a Material UI snackbars.
 *
 * @param openByDefault {boolean}
 * @returns {{isOpen: boolean, close: function, open: function}}
 */
export function useSnackbar(openByDefault = false) {
    const [isOpen, setOpen] = useState(openByDefault);

    const open = () => {
        setOpen(true);
    };

    const close = (event, reason) => {
        if (reason === 'clickaway') {
            return;
        }

        setOpen(false);
    };

    return {isOpen, open, close};
}