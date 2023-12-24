import {useState} from "react";

const Forecast = ({cityData}) => {
    const [unit, setUnit] = useState("C");

    const handleUnitChange = (e) => {
        setUnit(e.target.value);
    };

    return (
        <div className="rounded-lg shadow-lg bg-white p-10 flex items-center justify-center scale-125 mt-20">
            <div className="flex flex-col">
                <div className="flex flex-row">
                    <h2 className="text-2xl font-medium text-blue-500">
                        {cityData.name}
                    </h2>
                    <div id="tempUnitChanger" className="ml-auto">
                        <div className="inline-flex">
                            <div className="switch-field">
                                <input
                                    type="radio"
                                    id="radio-one"
                                    name="tempUnitChanger"
                                    value="F"
                                    checked={unit === 'F'}
                                    onChange={handleUnitChange}
                                />
                                <label htmlFor="radio-one">F</label>
                                <input
                                    type="radio"
                                    id="radio-two"
                                    name="tempUnitChanger"
                                    checked={unit === 'C'}
                                    value="C"
                                    onChange={handleUnitChange}
                                />
                                <label htmlFor="radio-two">C</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="flex flex-row justify-center gap-20">
                    <div className="flex flex-col">
                        <div className="flex flex-row items-center gap-5">
                            <img
                                src={`/assets/${cityData.details.condition.replace(' ', '_').toLowerCase()}.png`}
                                alt="weathericon"
                                className="w-20"
                            />
                            <span className="text-9xl text-blue-400">
                                {unit === 'C' ? cityData.temp.celsius : cityData.temp.fahrenheit}
                            </span>
                        </div>
                    </div>
                    <table className="text-blue-400 w-1/2">
                        <tbody>
                        <tr>
                            <td>Wind:</td>
                            <td className="text-right">
                                {cityData.details.wind} km/h
                            </td>
                        </tr>
                        <tr>
                            <td>Humidity:</td>
                            <td className="text-right">
                                {cityData.details.humidity}%
                            </td>
                        </tr>
                        <tr>
                            <td>Condition:</td>
                            <td className="text-right">
                                {cityData.details.condition}
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
};

export default Forecast;
