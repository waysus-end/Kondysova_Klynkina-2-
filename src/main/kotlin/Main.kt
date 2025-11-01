fun main()
{
    while (true)
    {
        println("╔═══════════════════════════════╗")
        println("║ МЕНЮ ПРОГРАММ                 ║")
        println("╠═══════════════════════════════╣")
        println("║ 1. Задача 1                   ║")
        println("║ 2. Задача 2                   ║")
        println("║ 3. Задача 3                   ║")
        println("║ 4. Задача 4                   ║")
        println("║ 5. Задача 5                   ║")
        println("║ 0. Выход                      ║")
        println("╚═══════════════════════════════╝")
        print("Выберите программу (0-5): ")

        val choice = readln().toIntOrNull()

        when (choice)
        {
            0 -> {
                println("Выход из программы.")
                return
            }
            1 -> program1()
            2 -> program2()
            3 -> program3()
            4 -> program4()
            5 -> program5()
            else -> println("Ошибка: выберите число от 0 до 5")
        }
        println("\nНажмите Enter для продолжения...")
        readln()
    }
}



fun program1()
{
    println("Программа запрашивает двухмерный массив и подсчитывает колличество различных цифр используемых в нем.")
    println("Введите количество строк:")
    val rows = readLine()!!.toInt()
    println("Введите количество столбцов:")
    val cols = readLine()!!.toInt()
    val arr = Array(rows) { IntArray(cols) }
    println("Введите массив размером $rows * $cols, каждое число через пробел, а каждая строчка через enter:")
    for (i in 0 until rows)
    {
        val line = readLine()!!.split(" ")
        for (j in 0 until cols)
        {
            arr[i][j] = line[j].toInt()
        }
    }
    val digits = mutableSetOf<Char>()
    for (i in 0 until rows)
    {
        for (j in 0 until cols)
        {
            val numStr = arr[i][j].toString()
            for (ch in numStr)
            {
                digits.add(ch)
            }
        }
    }
    println("Двумерный массив:")
    for (i in 0 until rows)
    {
        for (j in 0 until cols)
        {
            print("${arr[i][j]} \t")
        }
        println()
    }
    println("В массиве использовано ${digits.size} различных цифр")
}




fun program2()
{
    val size = 5
    val arr = Array(size) { IntArray(size) }
    println("Программа проверяет симметричен ли массив относительно главное диагонали.")
    println("Заполните массив 5*5 числами, каждое число через пробел, а каждая строчка через enter:")
    for (i in 0 until size)
    {
        val line = readLine()!!.split(" ")
        for (j in 0 until size)
        {
            arr[i][j] = line[j].toInt()
        }
    }
    var symmetric = true
    for (i in 0 until size)
    {
        for (j in i + 1 until size)
        {
            if (arr[i][j] != arr[j][i])
            {
                symmetric = false
                break
            }
        }
        if (!symmetric)
            break
    }
    if (symmetric)
    {
        println("Массив симметричен относительно главной диагонали")
    }
    else
    {
        println("Массив НЕ симметричен относительно главной диагонали")
    }
}




fun program3()
{
    val alphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЬЫЪЭЮЯ"
    println("Программа шифрует и расшифровывает слова заданные пользователем с помощью ключевого слова.")
    println("Выберите 1 - Зашифровать, 2 - Расшифровать:")
    val choice = readLine()!!.toInt()
    println("Введите ключевое слово:")
    val key = readLine()!!.uppercase()
    println("Введите текст:")
    val text = readLine()!!
    var result = ""
    var keyPos = 0
    for (i in text)
    {
        val pos = alphabet.indexOf(i.uppercaseChar())
        if (pos == -1)
        {
            result += i
        }
        else
        {
            val shift = alphabet.indexOf(key[keyPos])
            val newPos = if (choice == 1)
                (pos + shift) % alphabet.length
            else
                (pos - shift + alphabet.length) % alphabet.length
            result += alphabet[newPos]
            keyPos = (keyPos + 1) % key.length
        }
    }
    println("Результат:")
    println(result)
}



fun program4()
{
    println("Программа проверяет пересечение двух массивов, которые ввел пользователь.")
    println("Введите первый массив чисел через пробел:")
    val input1 = readLine()!!
    val arr1 = input1.split(" ")
    val list1 = mutableListOf<Int>()
    for (s in arr1)
    {
        list1.add(s.toInt())
    }
    println("Введите второй массив чисел через пробел:")
    val input2 = readLine()!!
    val arr2 = input2.split(" ")
    val list2 = mutableListOf<Int>()
    for (s in arr2)
    {
        list2.add(s.toInt())
    }
    val result = mutableListOf<Int>()
    for (num in list1)
    {
        if (list2.contains(num))
        {
            result.add(num)
            list2.remove(num)
        }
    }
    if (result.isEmpty())
    {
        println("Пересечений нет, попробуйте другие массивы")
    }
    else
    {
        println("Пересечение массивов с учётом повторов:")
        for (num in result)
        {
            print("$num ")
        }
    }
}



fun program5()
{
    println("Программа выводит слова из массива, который ввел пользователь, по признаку 'состоят из одинаковых букв'.")
        println("Введите слова через пробел:")
        val input = readLine()!!
        val words = input.split(" ")
        val shown = mutableListOf<String>()
        for (i in 0 until words.size)
        {
            if (shown.contains(words[i]))
                continue
            val letters1 = words[i].lowercase().toMutableList()
            letters1.sort()
            val group = mutableListOf<String>()
            for (j in 0 until words.size)
            {
                if (i == j || shown.contains(words[j]))
                    continue
                val letters2 = words[j].lowercase().toMutableList()
                letters2.sort()
                if (letters1 == letters2)
                {
                    group.add(words[j])
                    shown.add(words[j])
                }
            }
            group.add(words[i])
            shown.add(words[i])
            println(group.joinToString(", "))
        }
    }

