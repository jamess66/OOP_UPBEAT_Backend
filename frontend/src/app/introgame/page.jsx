import React from "react";
import "../styles/newgame.css";
import Link from "next/link";
function introgame() {
  return (
    <header className="background">
      <Link href="/setplayergame">
        <button className="Button1">NEW GAME</button>
      </Link>
      <Link href="/config_game">
        <button className="Button2">GAME CONFIG</button>
      </Link>
    </header>
  );
}

export default introgame;
