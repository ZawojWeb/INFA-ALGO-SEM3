import "./App.css"
import SingleCircle from "./SingleCircle"
import { useState, useEffect } from "react"
import ReactLoading from "react-loading"
function App() {
  const [circles, setCircles] = useState([])

  const [howMany, setHowMany] = useState(0)
  const [isLoading, setisLoading] = useState()

  const inputHandler = (e) => {
    setHowMany(e.target.value)
  }

  const rerender = async (e) => {
    e.preventDefault()
    if (isLoading) return
    setisLoading(true)
    await new Promise((resolve) => setTimeout(resolve, 200))
    const newArray = Array.from({ length: howMany }).map((_, i) => <SingleCircle positionX={Math.random() * 1000} positionY={Math.random() * 1000} key={i} />)
    setCircles(newArray)
    setisLoading(false)
  }

  useEffect(() => {
    setisLoading(false)
  }, [circles])

  return (
    <div className='App'>
      <header className='App-header'>
        <form className='form' style={{ display: "flex", alignItems: "center" }}>
          {isLoading && <ReactLoading type='spinningBubbles' color={"#ffffff"} height={20} width={20} />}
          <input type='text' value={howMany} onChange={inputHandler} />
          <button
            onClick={(e) => {
              setisLoading(true)
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
