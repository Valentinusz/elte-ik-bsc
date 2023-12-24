import { useState } from "react";

export const Box = ({x, y, color}) => {
  return (
    <div
      style={{
        position: "absolute",
        top: `${y}px`,
        left: `${x}px`,
        backgroundColor: color,
        width: "100px",
        height: "100px",
      }}
    ></div>
  );
};
