// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

enum class Nivel { INICIANTE, INTERMEDIARIO, AVANCADO }

data class Usuario(val userId:Int, val nome: String, var idade: Int, var xp: Int, var nivel: Nivel = Nivel.INICIANTE)

data class ConteudoEducacional(val nome: String, val duracao: Int = 60, val nivel: Nivel)

data class Formacao(val nome: String, var conteudos: List<ConteudoEducacional>, val nivel: Nivel) {

    val inscritos = mutableListOf<Usuario>()

    fun matricular(usuario: Usuario) {
        inscritos.add(usuario)
    }
}

fun main() {

    TODO("Simule alguns cenários de teste. Para isso, crie alguns objetos usando as classes em questão.")
}
