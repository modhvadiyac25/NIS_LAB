#include <bits/stdc++.h>
#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;
#define ll long long
string plain_text, cipher_text, final_text, key;
ll m;
string encrypt(string plain_text, string key, ll m)
{
cipher_text = plain_text;
ll i, j;
for (i = 0; i < plain_text.length(); i++)
{
j = ((plain_text[i] - 'a') + (key[i % m] - 'a')) % 26;
cipher_text[i] = j + 'a';
}
return cipher_text;
}
string decrypt(string cipher_text, string key, ll m)
{
ll i, j;
final_text = plain_text;
for (i = 0; i < plain_text.length(); i++)
{
j = ((cipher_text[i] - 'a') - (key[i % m] - 'a')) % 26;
if (j < 0)
{
j += 26;
}
final_text[i] = j + 'a';
}
return final_text;
}
void cryptAnalysis(string ct)
{
float english_freq[] = {8.167, 1.492, 2.782, 4.253, 12.702, 2.228, 2.015, 6.094, 6.996, 0.153, 0.772, 4.025, 2.406, 6.749, 7.507, 1.929, 0.095, 5.987, 6.327, 9.056, 2.758, 0.978, 2.360, 0.150, 1.974, 0.074};
float p[26];
for (int i = 0; i < 26; i++)
{
p[i] = english_freq[i] / 100;
}
for (int m = 3; m < ct.length() / 3; m++)
{
bool isCorrectLen = true;
vector<char> Y[m];
for (int i = 0; i < ct.length(); i++)
{
Y[i % m].push_back(ct.at(i));
}
cout << "\nm = " << m << "\n";
for (int i = 0; i < m; i++)
{
cout << "Y" << i + 1 << " : ";
for (int j = 0; j < Y[i].size(); j++)
{
cout << Y[i][j];
}
cout << "\n";
}
float icsum[m] = {0.0};
int freq[26] = {0};
float q[m][26];
for (int j = 0; j < m; j++)
{
for (int i = 0; i < 26; i++)
{
freq[i] = 0;
}
for (int i = 0; i < Y[j].size(); i++)
{
freq[Y[j][i] - 'a'] += 1;
}
for (int i = 0; i < 26; i++)
{
q[j][i] = (float)(freq[i]) / Y[j].size();
}
for (int i = 0; i < 26; i++)
{
icsum[j] += q[j][i] * q[j][i];
}
cout << "IC of Y" << j + 1 << " : " << icsum[j] << "\n";
if (icsum[j] < 0.06)
{
isCorrectLen = false;
break;
}
}
if (isCorrectLen)
{
char key[m];
for (int j = 0; j < m; j++)
{
cout << "\nFor Y" << j + 1 << "\n";
vector<float> sum(26);
for (int k = 0; k < 26; k++)
{
sum[k] = 0.0f;
for (int i = 0; i < 26; i++)
{
sum[k] += (p[i] * q[j][(i + k) % 26]);
}
cout << "Sum = " << sum[k] << ";\t\tk = " << k << "\n";
}
int index = max_element(sum.begin(), sum.end()) - sum.begin();
key[j] = (index + 'a');
cout << "Key value = " << key[j] << "\n";
cout << "\n";
}
cout << "Key = ";
for (int i = 0; i < m; i++)
{
cout << key[i];
}
cout << "\n";
break;
}
}
}
int main()
{
ios_base::sync_with_stdio(false);
cin.tie(NULL);
cout << "Enter plain text : " << endl;
getline(cin, plain_text);
cout << "Enter key text : " << endl;
cin >> key;
m = key.length();
cipher_text = encrypt(plain_text, key, m);
cout << "Encrypted message is : " << cipher_text << endl;
final_text = decrypt(cipher_text, key, m);
cout << "Decrypted message is : " << final_text << endl;
cout << "Crypt Analysis" << endl;
cryptAnalysis(cipher_text);
return 0;
}