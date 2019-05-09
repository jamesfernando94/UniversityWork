class State(var left: RiverSideState, var right: RiverSideState, var boatPosition: PossibleBoatPositions.Value, var previousState : State) {

	def isAcceptable(): Unit = {
		if (
			left.missionaryCount < left.cannibalCount
				|| right.missionaryCount < right.cannibalCount
				|| boatPosition == PossibleBoatPositions.Right && right.cannibalCount + right.missionaryCount == 0
				|| boatPosition == PossibleBoatPositions.Left && left.cannibalCount + left.missionaryCount == 0) {
			return false
		}
		return true
	}
}