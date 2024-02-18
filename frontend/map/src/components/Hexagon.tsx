import "./Hexagom.css";

function Hexagon() {
  const column = 15;
  const row = 20;

  const arr: number[] = Array(column).fill(0) || [];
  const arr2: number[][] = Array(row).fill(arr) || [];

  return (
    <div
      style={{
        marginTop: "10px",
        marginLeft: "30%",
      }}
    >
      {arr2.map((data, key) => {
        return (
          <div
            style={{
              display: "flex",
              marginLeft: "5%",
            }}
            key={key}
          >
            {data.map((data2, k) => {
              return (
                <div
                  key={k}
                  style={{
                    transform: `translate(-${(35 * k) / 4}px,${
                      k % 2 === 0 ? "21px" : "0px"
                    }`,
                    marginTop: k % 2 === 0 ? "3px" : "0px",
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

export default Hexagon;