import {useSelector} from 'react-redux';
import {selectIsAuthenticated} from '../../state/authSlice.js';
import {Navigate} from 'react-router-dom';

/**
 * Wrapper placed around the children of React router Route element. Redirects to the supplied route if the user is not
 * authenticated.
 *
 * @param children route children.
 * @param redirectTo fallback route to redirect when authentication fails.
 * @returns {JSX.Element|*}
 * @constructor
 */
export function ProtectedRoute({ children, redirectTo }) {
    const isAuthenticated = useSelector(selectIsAuthenticated);

    if (!isAuthenticated) {
        return <Navigate to={redirectTo} state={{redirected: true}}/>;
    }

    return children;
}