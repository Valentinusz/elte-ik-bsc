import {useState} from "react";

const CitiesList = ({weatherList, handleCityChange}) => {
    // TODO: Jelenítsd meg az alábbi listában a városokat a weatherList tömb alapján
    // TODO: A listaelemekre (városnevek) kattintva hívódjon meg a handleCityChange függvény


    return (
        <ul className="gap-5 flex flex-row mt-20" id="citiesList">
            {weatherList.map(weather => (
                <li key={weather.id}
                    className="bg-blue-500 text-white p-2 text-lg cursor-pointer select-none rounded-lg hover:scale-105 transition-transform shadow-lg"
                    onClick={() => handleCityChange(weather.id)}
                >
                    {weather.name}
                </li>
            ))}
        </ul>
    );
};

export default CitiesList;
