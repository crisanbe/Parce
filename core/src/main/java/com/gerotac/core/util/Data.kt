package com.gerotac.core.util

data class Requirements(
    val idRequirement: Int,
    val image: String,
    val requirement: String,
    val intervention: String,
    val date: String
)

object DummyData {
    val listRequirements = listOf(
        Requirements(
            idRequirement = 0,
            image = "https://randomuser.me/api/portraits/men/3.jpg",
            requirement = "Requerimiento #1 - Nombre",
            intervention = "Area de intervencion",
            date = "15 de Mayo del 2022 (fecha)"
        ),
        Requirements(
            idRequirement = 1,
            image = "https://randomuser.me/api/portraits/men/3.jpg",
            requirement = "Requerimiento #2 - Nombre",
            intervention = "Area de intervencion",
            date = "15 de Mayo del 2022 (fecha)"
        ),
        Requirements(
            idRequirement = 2,
            image = "https://randomuser.me/api/portraits/men/3.jpg",
            requirement = "Requerimiento #3 - Nombre",
            intervention = "Area de intervencion",
            date = "15 de Mayo del 2022 (fecha)"
        ),
        Requirements(
            idRequirement = 3,
            image = "https://randomuser.me/api/portraits/men/3.jpg",
            requirement = "Requerimiento #4 - Nombre",
            intervention = "Area de intervencion",
            date = "15 de Mayo del 2022 (fecha)"
        ),
        Requirements(
            idRequirement = 4,
            image = "https://randomuser.me/api/portraits/men/3.jpg",
            requirement = "Requerimiento #5 - Nombre",
            intervention = "Area de intervencion",
            date = "15 de Mayo del 2022 (fecha)"
        ),
        Requirements(
            idRequirement = 5,
            image = "https://randomuser.me/api/portraits/men/3.jpg",
            requirement = "Requerimiento #6 - Nombre",
            intervention = "Area de intervencion",
            date = "15 de Mayo del 2022 (fecha)"
        ),
        Requirements(
            idRequirement = 6,
            image = "https://randomuser.me/api/portraits/men/3.jpg",
            requirement = "Requerimiento #7 - Nombre",
            intervention = "Area de intervencion",
            date = "15 de Mayo del 2022 (fecha)"
        ),
        Requirements(
            idRequirement = 7,
            image = "https://randomuser.me/api/portraits/men/3.jpg",
            requirement = "Requerimiento #8 - Nombre",
            intervention = "Area de intervencion",
            date = "15 de Mayo del 2022 (fecha)"
        )
    )
}