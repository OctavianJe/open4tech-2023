from flask import Flask, request, jsonify
from transformers import AutoTokenizer, AutoModelForSeq2SeqLM

app = Flask(__name__)

# Load the pretrained model and tokenizer
tokenizer = AutoTokenizer.from_pretrained("jordiclive/flan-t5-3b-summarizer")
model = AutoModelForSeq2SeqLM.from_pretrained("jordiclive/flan-t5-3b-summarizer")

@app.route('/summarize', methods=['POST'])
def summarize():
    # Get the text from the POST request
    data = request.get_json(force=True)
    raw_document = data['text']

    # Format the input text
    prompt = "Produce an article summary of the following news article:"
    sample_input = f"{prompt} {raw_document}"

    # Tokenize the input text
    tokens = tokenizer.encode_plus(sample_input, return_tensors="pt", max_length=100, truncation=True)

    # Use the model to generate a summary
    summary_ids = model.generate(tokens['input_ids'], num_beams=4, max_length=100, early_stopping=True)
    summary = tokenizer.decode(summary_ids[0], skip_special_tokens=True)

    # Return the summary
    return jsonify(summary=summary)

if __name__ == '__main__':
    app.run(port=5000, debug=True)
