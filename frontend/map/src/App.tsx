import "./App.css";

function App() {
  const column = 15;
  const row = 20;

  const arr: number[] = Array(column).fill(0) || [];
  const arr2: number[][] = Array(row).fill(arr) || [];

  return (
    <div>
      {arr2.map((data, key) => {
        return (
          <div
            style={{
              display: "flex",
              marginLeft: "500px",
            }}
            key={key}
          >
            {data.map((data2, k) => {
              return (
                <div
                  key={k}
                  style={{
                    transform: `translate(-${(25 * k) / 4}px,${
                      k % 2 === 0 ? "25px" : "0px"
                    }`,
                    marginTop: k % 2 === 0 ? "5px" : "0px",
                  }}
                  className={"hex-grid-content"}
                ></div>
              );
            })}
          </div>
        );
      })}
    </div>
  );
}

export default App;
