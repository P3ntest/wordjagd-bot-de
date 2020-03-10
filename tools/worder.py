inputFile = open("input.txt",'r',encoding = 'ansi')
outputFile = open("output.txt",'w',encoding = 'ansi')

cont = True

lines_seen = set() # holds lines already seen

while (cont):
    string = inputFile.readline()
    if (string == ''):
        cont = False
    else:
        use = True
        ssst = string.split("/")[0]
        if (ssst == ssst.upper()):
            use = False
        ssst = ssst.lower();
        if (not ssst.endswith('\n')):
            ssst += '\n'
        if (len(ssst) < 4):
            use = False
        if (len(ssst) > 11):
            use = False
        if (use):
            if (ssst not in lines_seen):
                outputFile.write(ssst)
                lines_seen.add(ssst)
outputFile.close()
inputFile.close()
