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
    println("Введите количество строк:")
    val rows = readLine()!!.toInt()
    println("Введите количество столбцов:")
    val cols = readLine()!!.toInt()
    val arr = Array(rows) { IntArray(cols) }
    println("Введите $rows * $cols трехзначных чисел по одному на строку:")
    for (i in 0 until rows)
    {
        for (j in 0 until cols)
        {
            arr[i][j] = readLine()!!.toInt()
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
    fun index(c: Char): Int
    {
        return alphabet.indexOf(c.uppercaseChar())
    }
    println("Выберите 1 - Зашифровать, 2 - Расшифровать:")
    val choice = readLine()!!.toInt()
    println("Введите ключевое слово:")
    val key = readLine()!!.uppercase()
    println("Введите текст:")
    val text = readLine()!!
    val result = StringBuilder()
    var keyPos = 0
    for (ch in text)
    {
        val pos = index(ch)
        if (pos == -1)
        {
            result.append(ch)
        }
        else
        {
            val shift = index(key[keyPos])
            val newPos = if (choice == 1)
            {
                (pos + shift) % alphabet.length
            }
            else
            {
                (pos - shift + alphabet.length) % alphabet.length
            }
            result.append(alphabet[newPos])
            keyPos = (keyPos + 1) % key.length
        }
    }
    println("Результат:")
    println(result.toString())
}



fun program4()
{
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
    println("Введите слова через пробел:")
    val input = readLine()!!
    val words = input.split(" ")
    val shown = mutableListOf<String>()
    for (i in 0 until words.size)
    {
        if (shown.contains(words[i]))
            continue

        val letters1 = mutableListOf<Char>()
        for (c in words[i])
        {
            val up = if (c in 'а'..'я') c else c
            letters1.add(up)
        }
        for (x in 0 until letters1.size - 1)
        {
            for (y in 0 until letters1.size - x - 1)
            {
                if (letters1[y] > letters1[y + 1])
                {
                    val temp = letters1[y]
                    letters1[y] = letters1[y + 1]
                    letters1[y + 1] = temp
                }
            }
        }

        val group = mutableListOf<String>()
        for (j in 0 until words.size)
        {
            if (i == j || shown.contains(words[j]))
                continue

            val letters2 = mutableListOf<Char>()
            for (c in words[j])
            {
                val up = if (c in 'а'..'я') c else c
                letters2.add(up)
            }
            for (x in 0 until letters2.size - 1)
            {
                for (y in 0 until letters2.size - x - 1)
                {
                    if (letters2[y] > letters2[y + 1])
                    {
                        val temp = letters2[y]
                        letters2[y] = letters2[y + 1]
                        letters2[y + 1] = temp
                    }
                }
            }

            var equal = true
            if (letters1.size != letters2.size)
            {
                equal = false
            }
            else
            {
                for (k in 0 until letters1.size)
                {
                    if (letters1[k] != letters2[k])
                    {
                        equal = false
                        break
                    }
                }
            }

            if (equal)
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
