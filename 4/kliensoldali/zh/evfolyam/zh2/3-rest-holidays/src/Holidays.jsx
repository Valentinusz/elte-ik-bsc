import {useGetCountriesQuery, useGetHolidaysQuery} from "./store/countryApiSlice.js";
import {useState} from "react";
import {useParams} from "react-router";

export const Holidays = () => {
    const [year, setYear] = useState(2023);
    const {country} = useParams();
    const {data: holidays, isLoading} = useGetHolidaysQuery({year, country});

    if (!isLoading && holidays) {
        console.log(holidays)

        return (
            <>
                <a href="/">Back</a>
                <table>
                    <thead>
                    <tr>
                        <th>Holidays</th>
                        <th>
                            <input type="number" value={year} onInput={event => setYear(event.target.value)}/>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    {holidays.map(holiday => (
                        <tr>
                            <td>{holiday.date}</td>
                            <td>{holiday.name}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </>
        );
    }
};
