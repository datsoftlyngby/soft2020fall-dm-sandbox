package dk.cphbusiness.dm.states

interface State {
    infix fun or(other: State): State = DisjuntionState(this, other)
    }

class ConcreteState() : State {

    }

class DisjuntionState(val first: State, val second: State) : State {

    }