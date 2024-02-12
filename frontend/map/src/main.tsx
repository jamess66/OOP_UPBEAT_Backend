import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App.tsx";
import "./index.css";
import InputPrompt from "./components/InputPrompt.tsx";
import Time from "./components/Time.tsx";
import Round from "./components/Round.tsx";

ReactDOM.createRoot(document.getElementById("root")!).render(
  <React.StrictMode>
    <div className="idmain">
      <InputPrompt />
      <Round />
      <Time />
      <App />
    </div>
  </React.StrictMode>
);
