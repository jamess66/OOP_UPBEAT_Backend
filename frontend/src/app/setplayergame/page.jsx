"use client";
import Link from "next/link";
import React, { useState } from "react";
import "../styles/setplayergame.css";
// import axios from "axios";
function playergame() {
  // const [username, setUsername] = useState("");
  // submit = async () => {
  //   e.preventdefault();
  //   try {
  //     await axios.post("http://localhost:3001/api/players", {
  //       username: username,
  //     });
  //   } catch (error) {
  //     console.log(error);
  //   }
  // };
  return (
    <header className="background">
      <div className="boderuser">
        <div className="component-setplayergame-front">
          USERNAME
          <form action="POST">
            <input
              className="textarea-styles"
              type="text"
              placeholder=" / Write your name "
              onChange={(e) => {
                setUsername(e.target.value);
              }}
            />
          </form>
          <div>
            <Link href="/gamestate">
              <button className="buttonjoin" value="Submit">
                JOIN GAME
              </button>
            </Link>
          </div>
          <div>
            <Link href="/introgame">
              <button className="buttonback">BACK</button>
            </Link>
          </div>
        </div>
      </div>
    </header>
  );
}
export default playergame;
