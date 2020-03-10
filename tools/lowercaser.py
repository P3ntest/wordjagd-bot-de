inputFile = open("extra.txt",'r',encoding = 'ansi')
outputFile = open("extra_output.txt",'w',encoding = 'ansi')

cont = True

lines_seen = set() # holds lines already seen

while (cont):
    string = inputFile.readline()
    if (string == ''):
        cont = False
    else:
        outputFile.write(string.lower())
outputFile.close()
inputFile.close()
