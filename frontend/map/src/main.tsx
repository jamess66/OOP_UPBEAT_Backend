import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App.tsx";
import "./index.css";
import InputPrompt from "./components/InputPrompt.tsx";
import Time from "./components/Time.tsx";

ReactDOM.createRoot(document.getElementById("root")!).render(
  <React.StrictMode>
    <div className="idmain">
      <InputPrompt />
      <Time />
      <App />
    </div>
  </React.StrictMode>
);
