"use client";
import React, { useEffect, useState, ChangeEvent } from "react";
import axios from "axios";
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

const fetchPlayer = async () => {
  const response = await axios.get(api + "/player");
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
  // console.log(HexGrid.rows);
  // console.log(HexGrid.cols);

  const column = 15;
  const row = 20;

  const arr: number[] = Array(HexGrid.cols).fill(0) || [];
  const arr2: number[][] = Array(HexGrid.rows).fill(arr) || [];

  const [isVisible, setIsVisible] = useState(false);
  const [zoom, setZoom] = useState(1);

  const handleClick = () => {
    setIsVisible(!isVisible);
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
