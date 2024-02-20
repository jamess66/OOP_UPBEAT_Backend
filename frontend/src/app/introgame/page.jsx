import React from "react";
import "../styles/newgame.css";
import Link from "next/link";
function introgame() {
  return (
    <header className="background">
      <Link href="/gamestate">
        <button className="Button1">NEW GAME</button>
      </Link>
      <Link href="/gameconfig">
        <button className="Button2">GAME CONFIG</button>
      </Link>
    </header>
  );
}

export default introgame;
