package dk.cphbusiness.dm.vssl

import dk.cphbusiness.dm.states.State

interface Ast {
    fun compute(precondition: State): State
    }

