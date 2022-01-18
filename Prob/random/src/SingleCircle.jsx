import React from "react"
import { Circle } from "react-shapes"
import styled from "styled-components"

const SingleCircle = ({ positionX, positionY, random }) => {
  return (
    <CircleContainer random={random} positionX={positionX} positionY={positionY}>
      <Circle className='circle' r={2} fill={{ color: "#E65243" }} stroke={{ color: "#E65243" }} strokeWidth={3} />
    </CircleContainer>
  )
}

const CircleContainer = styled.div`
  transform: translate(${(props) => props.positionX}px, -${(props) => props.positionY}px);
  position: absolute;
  max-height: 1px;
  max-width: 1px;
  font-size: 0;
  left: 0;
  bottom: 0;
`
export default SingleCircle
