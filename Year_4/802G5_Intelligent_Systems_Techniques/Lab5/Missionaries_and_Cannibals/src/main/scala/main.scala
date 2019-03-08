import scala.collection.mutable.ListBuffer

object main extends App {
	val initialState = new State(new RiverSideState(3, 3), new RiverSideState(0, 0), PossibleBoatPositions.Left)
	val targetState = new State(new RiverSideState(0, 0), new RiverSideState(3, 3), PossibleBoatPositions.Right)
	var exploredStates = new ListBuffer[State]

	override def main(args: Array[String]): Unit = {
		super.main(args)

		var currentState = initialState


	}

	def successors(currentState: State): List[State] = {
		var possibleStates = new ListBuffer[State]


	}



}


