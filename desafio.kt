package desafio

enum class Nivel { INICIANTE, INTERMEDIARIO, AVANCADO }

data class Usuario(val userId:Int = userIdCadastrados++, val nome: String, var idade: Int, var xp: Int = 0, var nivel: Nivel = Nivel.INICIANTE) {

    fun ganharXp(xpGanho:Int) {
        xp += xpGanho
        atualizarNivelUsuario()
    }

    private  fun atualizarNivelUsuario() {
        nivel = when {
            (xp < 1000) -> Nivel.INICIANTE
            (xp < 3000) -> Nivel.INTERMEDIARIO
            else -> Nivel.AVANCADO
        }
    }

}

data class ConteudoEducacional(val nome: String, val duracao: Int = 60, val nivel: Nivel = Nivel.INICIANTE)

data class Formacao(val nome: String, var conteudos: List<ConteudoEducacional>) {

    val inscritos = mutableListOf<Usuario>()

    fun matricular(usuario: Usuario) {

        if (usuario !in inscritos) {
            inscritos.add(usuario)
        } else {
            println("Usuário já está Matriculado na Formação ")
        }
    }

    fun concluirFormacao(usuario: Usuario) {
        if (usuario in inscritos) {
            val xpGanho = 1500
            usuario.ganharXp(xpGanho)
        } else {
            println("${usuario.nome} não está matriculado nesta formação.")
        }
    }
}

val usuariosCadastrados = mutableListOf<Usuario>()
var userIdCadastrados = usuariosCadastrados.size + 1

fun criarUsuario(nome:String, idade: Int):Usuario {
    var nomeUsuario = nome
    val nomeJaCadastrado = usuariosCadastrados.any { it.nome == nomeUsuario }

    while (nomeJaCadastrado) {
        println("Nome já cadastrado digite um nome diferente: ")
        nomeUsuario = readln()
    }

    val novoUsuario = Usuario(nome = nomeUsuario, idade =  idade)

    usuariosCadastrados.add(novoUsuario)

    return novoUsuario
}


fun passarConteudosParaFormacao(vararg conteudos:ConteudoEducacional):List<ConteudoEducacional> {
    val listaComConteudo = mutableListOf<ConteudoEducacional>()
    for (x in conteudos) {
        listaComConteudo.add(x)
    }
    val listaComConteudoLock: List<ConteudoEducacional> = listaComConteudo
    return listaComConteudoLock
}



fun main() {
    // Criando Conteudos
    val kotlinBasico = ConteudoEducacional("Kotlin Básico", 120)
    val kotlinIntermediario = ConteudoEducacional("Kotlin Intermediario", 140, Nivel.INTERMEDIARIO)
    val kotlinAvancado = ConteudoEducacional("Kotlin Avançado", 180, Nivel.AVANCADO)
    val javaBasico = ConteudoEducacional("Java Básico", 120)
    val javaIntermediario = ConteudoEducacional("Java Intermediario", 140, Nivel.INTERMEDIARIO)
    val javaAvancado = ConteudoEducacional("Java Avançado", 180, Nivel.AVANCADO)


    val conteudosKotlin = passarConteudosParaFormacao(kotlinBasico, kotlinAvancado, kotlinIntermediario)
    val conteudosJava = passarConteudosParaFormacao(javaBasico, javaIntermediario, javaAvancado)

    // Criando Formação
    val formacaoKotlin = Formacao("Kotlin Completo", conteudosKotlin)
    val formacaoJava = Formacao("Java Completo", conteudosJava)

    // Criando Usuarios
    val fernando = criarUsuario("Fernando", 23)
    val renan = criarUsuario("Renan", 19)
    val fabiana = criarUsuario("Fabiana", 32)
    val carlos = criarUsuario("Carlos", 35)

    // Matriculando usuarios
    formacaoKotlin.matricular(fernando)
    formacaoKotlin.matricular(renan)
    formacaoJava.matricular(fabiana)
    formacaoJava.matricular(carlos)

    // Quais formações tenho?
    println("Formações registradas:")
    println("Nome: ${formacaoKotlin.nome}")
    println("Conteúdos: ${formacaoKotlin.conteudos}")
    print("Usuários inscritos: ")
    formacaoKotlin.inscritos.forEach { print(it.nome + " ") }
    println()
    println("Nome: ${formacaoJava.nome}")
    println("Conteúdos: ${formacaoJava.conteudos}")
    print("Usuários inscritos: ")
    formacaoJava.inscritos.forEach { print(it.nome + " ") }
    println()

    // Fernando e carlos concluiram a formação
    formacaoKotlin.concluirFormacao(fernando)
    formacaoJava.concluirFormacao(carlos)


    // Usuários do sistema ?
    println("usuários do sistema:")
    for (x in usuariosCadastrados) {
        println("Id: ${x.userId} | Nome: ${x.nome}")
        println("idade: ${x.idade}")
        println("XP: ${x.xp} | Nível: ${x.nivel}")
        println("-----------------------------------")
    }



}
