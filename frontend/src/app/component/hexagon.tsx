"use client";
import React, { useEffect, useState, ChangeEvent } from "react";
import axios from "axios";
import {
  TransformWrapper,
  TransformComponent,
  useControls,
} from "react-zoom-pan-pinch";

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
  rows: number;
  cols: number;
}
interface Crew {
  budget: number;
  cityCenter: string;
}
interface CrewCommands {
  constructionPlan: string;
  playerName: string;
}
interface PlayerInstance {
  playerName: string;
  budget: number;
}
const api = "http://localhost:8080";
const fetchData = async () => {
  const response = await axios.get(api + "/territory");
  return response.data;
};

const xy = async () => {
  const response = await axios.get(api + "/");
  return response.data;
};

function ProfileBox({
  isVisible,
  handleClick,
}: {
  isVisible: boolean;
  handleClick: () => void;
}) {
  const [Player, setPlayer] = useState<PlayerInstance>({
    playerName: "",
    budget: 0,
  });

  useEffect(() => {
    fetchData()
      .then((data: PlayerInstance) => {
        setPlayer(data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);
  console.log(Player.playerName);
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
      {/* <div className="box_cover"></div> */}
    </div>
  );
}

function InputForm() {
  const [value, setValue] = useState("");

  const handleChange = (event: ChangeEvent<HTMLTextAreaElement>) => {
    setValue(event.target.value);
  };
  return (
    <form className="input">
      <textarea
        value={value}
        onChange={handleChange}
        placeholder=" / Write command here"
        className="input_box"
        style={{
          resize: "none",
        }}
      ></textarea>
      <div>
        <button
          className=" bg-lime-500 size-7/12 min-h-12 uppercase rounded-2xl"
          style={{
            marginLeft: "85px",
            fontFamily: "Anakotmai",
            fontWeight: "bold",
          }}
        >
          submit
        </button>
      </div>
    </form>
  );
}

function Hexagon() {
  const [HexGrid, setHexGrid] = useState<HexGrid>({
    grid: [],
    rows: 0,
    cols: 0,
  });
  useEffect(() => {
    fetchData()
      .then((data: HexGrid) => {
        setHexGrid(data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);
  console.log(HexGrid.grid);
  const [HexRegion, setHexregion] = useState<HexRegion>({
    ownerHashcode: 0,
    deposit: 0,
    max_deposit: 0,
    x: 0,
    y: 0,
  });
  useEffect(() => {
    fetchData()
      .then((data: HexRegion) => {
        setHexregion(data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  const arr: number[] = Array(HexGrid.cols).fill(0) || [];
  const arr2: number[][] = Array(HexGrid.rows).fill(arr) || [];

  const [isVisible, setIsVisible] = useState(false);
  const [zoom, setZoom] = useState(1);

  const Controls = () => {
    const { zoomIn, zoomOut, resetTransform } = useControls();
    return (
      <>
        <button className=" bg-orange-300" onClick={() => zoomIn()}>
          Zoom In
        </button>
        <button onClick={() => zoomOut()}>Zoom Out</button>
        <button onClick={() => resetTransform()}>Reset</button>
      </>
    );
  };
  const handleClick = () => {
    setIsVisible(!isVisible);
  };

  const hexSize = 50; // Set this to the size of your hexagons
  const gridWidth = HexGrid.cols * hexSize;
  const gridHeight = HexGrid.rows * hexSize;

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
        {/* <div className="Round"> ROUND :</div> */}
        <div className="Time">TIME :</div>
        <ProfileBox isVisible={isVisible} handleClick={handleClick} />
        <InputForm />
      </div>
      <div
        style={{
          marginTop: "45px",
          marginLeft: "-100%",
          flexDirection: "row-reverse",
          justifyContent: "flex-end",
          width: `${gridWidth}px`,
          height: `${gridHeight}px`,
          overflow: "auto",
          border: "5px solid green",
        }}
      >
        <TransformWrapper>
          <Controls />
          <TransformComponent>
            {arr2.map((data, i) => {
              return (
                <div
                  style={{
                    display: "flex",
                    justifyContent: "flex-end",
                  }}
                  key={i}
                >
                  {data.map((data2, j) => {
                    return (
                      <div
                        key={j}
                        style={{
                          transform: `translate(-${(35 * j) / 4}px, ${
                            j % 2 === 0 ? "21px" : "0px"
                          })`,
                          marginTop: j % 2 === 0 ? "3px" : "0px",
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
                          {`(${HexGrid.grid[i][j].deposit}, ${HexGrid.grid[i][j].deposit})`}
                        </div>
                      </div>
                    );
                  })}
                </div>
              );
            })}
          </TransformComponent>
        </TransformWrapper>
      </div>
    </div>
  );
}

export default Hexagon;
