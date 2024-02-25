"use client";
import React, { useEffect, useState } from "react";
import "../component/ui/hexagom.css";
import "../component/ui/prompt.css";
import "../component/ui/profile.css";
import "../component/ui/Round.css";
import "../component/ui/Time.css";

interface HexRegion {
  ownerHashcode: number;
  deposit: number;
  max_deposit: number;
  x: number;
  y: number;
}

interface HexGrid {
  grid: HexRegion[][];
  row: number;
  col: number;
}
async function fetchUsers(): Promise<HexGrid[]> {
  const response = await fetch("http://localhost:8080/territory");
  const data = await response.json();
  return data;
}

function CreateHexagon() {
  const [Hexagon, setHexagon] = useState<HexGrid[]>([]);
  useEffect(() => {
    fetchUsers().then((data) => setHexagon(data));
  }, []);
}
function Hexagon() {
  const column = 15;
  const row = 20;

  const arr: number[] = Array(column).fill(0) || [];
  const arr2: number[][] = Array(row).fill(arr) || [];

  const [isVisible, setIsVisible] = useState(false);
  const [zoom, setZoom] = useState(1);

  const handleClick = () => {
    setIsVisible(!isVisible);
  };

  const zoomIn = () => {
    setZoom((prevZoom) => prevZoom + 0.1);
  };

  const zoomOut = () => {
    setZoom((prevZoom) => prevZoom - 0.1);
  };

  return (
    // <div>
    //   <button onClick={zoomIn} className=" bg-white">
    //     Zoom In
    //   </button>
    //   <button onClick={zoomOut}>Zoom Out</button>

    <div
      style={{
        marginTop: "45px",
        marginLeft: "45%",
        // transform: `scale(${zoom})`,
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
    // </div>
  );
}

export default Hexagon;
