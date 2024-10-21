# -*- coding: utf-8 -*-
"""Cash_advance.ipynb

Automatically generated by Colab.

Original file is located at
    https://colab.research.google.com/drive/1ybUiwfVgdwmlimrYVDK7iwku_yFfsmAJ
"""

!pip install -U openai-whisper

!pip install git+https://github.com/openai/whisper.git

!pip install --upgrade --no-deps --force-reinstall git+https://github.com/openai/whisper.git

!pip install setuptools-rust

import whisper

# Load the whisper model
model = whisper.load_model("base")

# Transcribe audio (assuming you have an audio file 'audio.mp3')
result = model.transcribe("cash.aac")

# The result contains text and other details
print(result['text'])

!pip install langchain_groq

from langchain_groq import ChatGroq

llm = ChatGroq(
    temperature=0,
    groq_api_key='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',
    model_name="llama-3.1-70b-versatile"
)
response = llm.invoke("The first person to land on moon was ...")
print(response.content)

user_input = result['text']

# Print the processed data
print(user_input)

!pip install langchain

from langchain import LLMChain, PromptTemplate

template = """
Extract the following information from the user's text:
1. Cash Advance amount
2. Monthly repayment amount
3. Bonus (if applicable)
Return the information in a structured JSON format:
{{
  "Cash_Advance": "<cash advance amount>",
  "Repayment_Monthly": "<monthly repayment amount>",
  "Bonus": "<bonus amount>"
}}
User Input: {user_input}
### VALID JSON (NO PREAMBLE):
"""

prompt = PromptTemplate(input_variables=["user_input"], template=template)
llm_chain = LLMChain(prompt=prompt, llm=llm)

response = llm_chain.run({
    "user_input": user_input  # Input the text containing the information
})

# Print the extracted response
print(response)

import sqlite3
from datetime import datetime

# Connect to SQLite database
conn = sqlite3.connect('dataabase.db')
cursor = conn.cursor()

# Insert into Cash_Advance table
def store_cash_advance(worker_id, employer_id, amount, bonus, status="Pending"):
    advance_date = datetime.now().strftime('%Y-%m-%d')
    cursor.execute("""
        INSERT INTO Cash_Advance (WorkerID, EmployerID, Amount,Bonus, AdvanceDate, Status)
        VALUES (?, ?, ?, ?, ?, ?)
    """, (worker_id, employer_id, amount, bonus, advance_date, status))
    conn.commit()

# Insert into Repayment table
def store_repayment(advance_id, worker_id, employer_id, amount_paid):
    payment_date = datetime.now().strftime('%Y-%m-%d')
    cursor.execute("""
        INSERT INTO Repayment (AdvanceID, WorkerID, EmployerID, AmountPaid, PaymentDate)
        VALUES (?, ?, ?, ?, ?)
    """, (advance_id, worker_id, employer_id, amount_paid, payment_date))
    conn.commit()

# Example Usage:
# Store the confirmed data
store_cash_advance('w001', 'e001', 1000)
store_repayment(1, 'w001', 'e001', 200)