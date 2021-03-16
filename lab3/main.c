#include <stdio.h>
#include <string.h>
int main()
{
  float english_freq[]={8.167,1.492,2.782,4.253,12.702,2.228,2.015,6.094,6.996,0.153,0.772,4.025,2.406,6.749,7.507,1.929,0.095,5.987,6.327,9.056,2.758,0.978,2.360,0.150,1.974,0.074};
  float p[26];

  for(int i=0;i<26;i++)
  {
   p[i]=english_freq[i]/100;
  }

  //char Y1[]="lwgwcraoktepgtqctjvuegvguqgecvprpvjgtjeugcjg";
  //char Y1[]="igggqhgwgkvctsosqswvwfvyshsvfshzhwwfsohcoqsl";
  //char Y1[]="ofdhurwqzklzhgvvluvlszwhwkhfdukdhviwhuhfwluw";
  char Y1[]="mevhcwilemwvvxgetmexlmlcxvelgmimbwxlgevvitx";
  int freq[26]={0};
  float q[26];

  for(int i=0;i<strlen(Y1);i++)
  {
    freq[Y1[i]-97]=freq[Y1[i]-97]+1;
  }
  
 for(int i=0;i<26;i++)
 {
   q[i]=(float)(freq[i])/strlen(Y1);
 }


for(int k=0;k<26;k++)
{
 float sum=0.0;

 for(int i=0;i<26;i++)
 {
   sum=sum+(p[i]*q[(i+k)%26]);
 }
 printf("sum=%f,k=%d\n",sum,k);
}
return 0;  
}

