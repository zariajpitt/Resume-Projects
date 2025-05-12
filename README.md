A repository for ML project I have in progress. It is an ML model (jyptff.ipynb) using tensorflow using federated learning and differential privacy.

Explanation of code

Federated Learning and Differential Privacy for COVID-19 X-ray Classification
1. Problem Statement:
Training deep learning models for medical image classification, like detecting COVID-19 from X-ray images, requires large datasets. Although collecting this data centrally raises privacy concerns. Federated Learning  lets models train across multiple devices without sharing raw data, while Differential Privacy adds noise and clipping to protect patient information. This project looks at how well FL and FL+DP perform on a COVID-19 X-ray dataset.

2. Introduction:
Deep learning has proven useful for medical image classification, but training these models requires massive amounts of data that could expose sensitive patient info. FL allows training on decentralized data, keeping it on local devices, and DP adds another layer of privacy by introducing noise and clipping. This study applies both methods to the COVID-19 Radiography dataset and compares the results.

3. Related Work:
Previous studies have shown FL works well for training models while keeping data private. DP is also widely used to prevent information leakage, but it can sometimes lower model accuracy. Research on combining FL and DP in healthcare shows potential but also highlights challenges in maintaining model performance.

4. Methodology:
Dataset: Used the COVID-19 Radiography dataset with four categories: COVID, Lung Opacity, Normal, and Viral Pneumonia. Images were resized to 64x64 pixels.
Model Architecture: A simple CNN with two convolutional layers, max-pooling, and dense layers using SoftMax activation for classification.
Federated Learning Setup:
Split data among five clients.
Each client trained locally for 3 epochs per round.
Used Federated Averaging to aggregate updates.
Global model updated and redistributed after each round.
Differential Privacy Implementation:
Used DP-SGD optimizer with gradient clipping and added noise.
Estimated privacy loss using Rényi Differential Privacy (ε = 29.45).
Measured how much accuracy dropped due to DP noise.
5. Results:
FL Training Results:
•	Round 1: Accuracy = 12.26%, Loss = 1.37
•	Round 2: Accuracy = 22.35%, Loss = 1.30
•	Round 3: Accuracy = 38.22%, Loss = 1.26
Model improved steadily over rounds as loss decreased.
FL+DP Training Results:
Privacy Budget (ε) = 29.45
Adding DP noise slowed down training and lowered accuracy.
FL+DP had worse accuracy than FL alone but provided stronger privacy protection.
6. Conclusion:
FL worked well for training without centralized data, and FL+DP added privacy at the cost of some accuracy. These methods could be useful in medical AI, where privacy is a major concern. The accuracy and loss still needs improvement so i will continue working on that.

