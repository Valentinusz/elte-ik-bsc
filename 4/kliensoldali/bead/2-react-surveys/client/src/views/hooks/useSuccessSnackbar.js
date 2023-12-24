import {useState} from 'react';
import {useSnackbar} from './useSnackbar.js';

/**
 * Custom hook for controlling the state of a Material UI snackbars that can either display the success or failure of an
 * action.
 * @param openByDefault {boolean}
 * @param successfulByDefault {boolean}
 * @returns {{isOpen: boolean, close: function, open: function}}
 */
export function useSuccessSnackbar(openByDefault = false, successfulByDefault = true) {
    const {isOpen, open, close} = useSnackbar(openByDefault);
    const [isSuccessful, setSuccess] = useState(successfulByDefault);

    const succeed = () => {
        setSuccess(true);
    };

    const fail = () => {
        setSuccess(fail);
    };

    return {isOpen, isSuccessful, open, close, succeed, fail};
}