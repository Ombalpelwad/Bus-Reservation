from gtts import gTTS

# Modify the text to be converted to speech
generated_text = "Hello, this is a new sample text for you!"

# Create a gTTS object
tts = gTTS(text=generated_text, lang='en')

# Save the audio output to a file
audio_file_path = "new_generated_speech.mp3"
tts.save(audio_file_path)

print(f"Audio saved to {audio_file_path}")
