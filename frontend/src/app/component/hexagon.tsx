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
function ProfileBox({
  isVisible,
  handleClick,
}: {
  isVisible: boolean;
  handleClick: () => void;
}) {
  return (
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
  );
}

function InputForm() {
  return (
    <form className="input">
      <input
        type="input"
        placeholder=" / Write command here"
        className="input_box"
      ></input>
    </form>
  );
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
    <div
      style={{
        display: "flex",
        flexDirection: "row",
        justifyContent: "space-between",
        alignItems: "flex-start",
      }}
    >
      <div
        style={{
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
          marginLeft: "12%",
          marginTop: "26%",
          flexShrink: 0,
        }}
      >
        <div className="Round"> ROUND :</div>
        <div className="Time">TIME :</div>
        <ProfileBox isVisible={isVisible} handleClick={handleClick} />
        <InputForm />
      </div>
      <div
        style={{
          marginTop: "45px",
          marginLeft: "20%",
          flexDirection: "row-reverse",
          justifyContent: "flex-end",
        }}
      >
        {arr2.map((data, y) => {
          return (
            <div
              style={{
                display: "flex",
                justifyContent: "flex-end",
              }}
              key={y}
            >
              {data.map((data2, x) => {
                return (
                  <div
                    key={x}
                    style={{
                      transform: `translate(-${(35 * x) / 4}px, ${
                        x % 2 === 0 ? "21px" : "0px"
                      })`,
                      marginTop: x % 2 === 0 ? "3px" : "0px",
                    }}
                    className={"hex-grid-content"}
                  >
                    <div
                      style={{
                        position: "relative",
                        color: "black",
                        fontSize: "10px",
                        alignItems: "center",
                        transform: `translate(-50%, -50%)`,
                        top: "50%",
                        left: "50%",
                      }}
                    >
                      {`(${x}, ${y})`}
                    </div>
                  </div>
                );
              })}
            </div>
          );
        })}
      </div>
    </div>
  );
}

export default Hexagon;
