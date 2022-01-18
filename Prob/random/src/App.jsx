import "./App.css"
import SingleCircle from "./SingleCircle"
import { useState, useEffect } from "react"
import ReactLoading from "react-loading"
function App() {
  const [circles, setCircles] = useState([])

  const [howMany, setHowMany] = useState(0)

  const inputHandler = (e) => {
    setHowMany(e.target.value)
  }

  const rerender = async (e) => {
    e.preventDefault()

    const newArray = Array.from({ length: howMany }).map((_, i) => <SingleCircle positionX={Math.random() * 1000} positionY={Math.random() * 1000} key={i} />)
    setCircles([...circles, newArray])
  }

  return (
    <div className='App'>
      <header className='App-header'>
        <form className='form' style={{ display: "flex", alignItems: "center" }}>
          <input type='text' value={howMany} onChange={inputHandler} />
          <button
            onClick={(e) => {
              rerender(e)
            }}
            type='submit'
          >
            Generate
          </button>
        </form>
        <div className='rec'>{circles}</div>
      </header>
    </div>
  )
}

export default App
