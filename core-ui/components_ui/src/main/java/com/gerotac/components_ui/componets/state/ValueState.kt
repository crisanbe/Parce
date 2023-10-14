package com.gerotac.components_ui.componets.state

class Value(value: String) : TextFieldValueState(validator = { true }, errorFor = {""})
class ValueState() : TextFieldValueState(validator = { true }, errorFor = {""})
