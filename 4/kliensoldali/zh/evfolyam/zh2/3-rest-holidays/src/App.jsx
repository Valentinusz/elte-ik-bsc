import "./App.css";
import {Holidays} from "./Holidays";
import {useGetCountriesQuery} from "./store/countryApiSlice.js";
import React from "react";
import {useParams} from "react-router";
import {NavLink} from "react-router-dom";

function App() {
    const {data: countries, isLoading} = useGetCountriesQuery();
    const {country} = useParams();

    if (isLoading) {
        return null;
    } else {
        console.log(countries)

        return (
            <>
                <div>
                    <table>
                        <thead>
                        <tr>
                            <th>Name</th>
                        </tr>
                        </thead>
                        <tbody>
                        {countries.map(country => (
                            <tr key={country.countryCode}>
                                <td>
                                    <NavLink to={`/${country.countryCode}`}>{country.name} ({country.countryCode})</NavLink>
                                </td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                </div>
                {country && <Holidays/>}
                <div>
                </div>
            </>
        );
    }
}

export default App;
