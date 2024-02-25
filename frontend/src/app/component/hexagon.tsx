"use client";
import React, { useState } from "react";
import "../component/ui/hexagom.css";
import "../component/ui/prompt.css";
import "../component/ui/profile.css";
import "../component/ui/Round.css";
import "../component/ui/Time.css";

function Hexagon() {
  const column = 15;
  const row = 20;

  const arr: number[] = Array(column).fill(0) || [];
  const arr2: number[][] = Array(row).fill(arr) || [];

  const [isVisible, setIsVisible] = useState(false);

  const handleClick = () => {
    setIsVisible(!isVisible);
  };

  return (
    <div
      style={{
        marginTop: "45px",
        marginLeft: "45%",
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
      <div className="Round"> ROUND :</div>
      <div className="Time">TIME :</div>
      <div className="box">
        {!isVisible && (
          <button
            className={`Profile ${isVisible ? "hidden" : ""}`}
            onClick={handleClick}
          >
            Profile
          </button>
        )}
        {isVisible && (
          <div
            className={`Profilehidden ${isVisible ? "" : "hidden"}`}
            onClick={handleClick}
          >
            Player1 :
            <br />
            Coin :
          </div>
        )}
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
