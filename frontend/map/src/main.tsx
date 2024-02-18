import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App.tsx";
import "./index.css";
import Time from "./components/Time.tsx";
import Round from "./components/Round.tsx";
import ShowStategame from "./components/ShowStategame.tsx";
import Hexagon from "./components/Hexagon.tsx";

ReactDOM.createRoot(document.getElementById("root")!).render(
  <React.StrictMode>
    <div className="idmain">
      <ShowStategame />
      <Round />
      <Time />
      {/* <App /> */}
      <Hexagon />
    </div>
  </React.StrictMode>
);
