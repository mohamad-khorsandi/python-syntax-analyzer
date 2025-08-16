# python syntax analyzer

this is a simple python syntax analyzer for educational purposes.
the project can detect syntax mistakes in code and suggests for correction.

#### grammar files
it contains several files for syntax inside grammar_src directory.
you can add syntax even for other languages as you wish. syntax files are at BNF form.
this is an example of these syntax files:
<img width="664" height="410" alt="Screenshot 2025-08-16 173841" src="https://github.com/user-attachments/assets/94ff5346-fab8-44ee-8c0e-6338ab873efb" />

#### code file
also there is a file for input python code: the file is inside pyCode.

#### test
let's test it with a wrong code:
<img width="512" height="530" alt="Screenshot 2025-08-16 180024" src="https://github.com/user-attachments/assets/b2c2b544-5853-49d1-8931-710c508e2925" />
<br/>
this code missed a colon at line 16. now this is the result:
<img width="465" height="229" alt="Screenshot 2025-08-16 180226" src="https://github.com/user-attachments/assets/361b4dc4-c91e-4834-af3a-438c9fed16ab" />
