import { Slider } from '@mui/material';

const marks = [
    { value: 5, label: '5°C' },
    { value: 10, label: '10°C'},
    { value: 15, label: '15°C'},
    { value: 20, label: '20°C'},
    { value: 25, label: '25°C'},
    { value: 30, label: '30°C'},

];
  
function valuetext(value) {
    return `${value}°C`;
}

export default function Thermostat({device}) {
    console.log(device);
    return (
        <>
            <Slider aria-label="Custom marks" defaultValue={20} getAriaValueText={valuetext} step={1}
                valueLabelDisplay="auto" marks={marks} min={5} max={30} label="Target temperature"
            />
        </>
    );
}