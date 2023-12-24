import {useState} from "react";
import {Box} from "./Box";

export const TaskB = () => {
    const [position, setPosition] = useState({
        x: 10,
        y: 10,
    });
    const [color, setColor] = useState("blue");

    return (
        <div>
            <h1>Task B</h1>
            <div>
                Color:
                <select onChange={(e) => setColor(e.target.value)}>
                    <option value="blue">blue</option>
                    <option value="red">red</option>
                    <option value="green">green</option>
                    <option value="yellow">yellow</option>
                </select>
            </div>
            <div>
                x:{" "}
                <input
                    type="number"
                    value={position.x}
                    onInput={(e) => setPosition({...position, x: parseInt(e.target.value)})}
                    size="5"
                />
                y:{" "}
                <input
                    type="number"
                    value={position.y}
                    onInput={(e) => setPosition({...position, y: parseInt(e.target.value)})}
                    size="5"
                />
            </div>
            <div style={{position: "relative"}}>
                <Box color={color} x={position.x} y={position.y}></Box>
            </div>
        </div>
    );
};
