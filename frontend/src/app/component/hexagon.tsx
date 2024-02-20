import React from "react";
import "../component/ui/hexagom.css";
import "../component/ui/prompt.css";
import "../component/ui/profile.css";

function Hexagon() {
  const column = 15;
  const row = 20;

  const arr: number[] = Array(column).fill(0) || [];
  const arr2: number[][] = Array(row).fill(arr) || [];

  return (
    <div
      style={{
        marginTop: "20px",
        marginLeft: "50%",
      }}
    >
      {arr2.map((data, key) => {
        return (
          <div
            style={{
              display: "flex",
              marginLeft: "25%",
            }}
            key={key}
          >
            {data.map((data2, k) => {
              return (
                <div
                  key={k}
                  style={{
                    transform: `translate(-${(35 * k) / 4}px, ${
                      k % 2 === 0 ? "21px" : "0px"
                    })`,
                    marginTop: k % 2 === 0 ? "3px" : "0px",
                  }}
                  className={"hex-grid-content"}
                ></div>
              );
            })}
          </div>
        );
      })}
      <div className="box">
        <button className="Profile">Profile</button>
        <div className="box_cover"></div>
      </div>
      <form className="input">
        <input
          type="input"
          placeholder=" / Write command here"
          className="input_box"
        ></input>
      </form>
    </div>
  );
}

export default Hexagon;
