#include "lsens.h"
#include "delay.h"

 //PF8 anolog����
//��ʼ������������
void Lsens_Init(void)
{
	GPIO_InitTypeDef  GPIO_InitStruct;
	
	RCC_APB2PeriphClockCmd(RCC_APB2Periph_GPIOF,ENABLE);
	
	GPIO_InitStruct.GPIO_Mode=GPIO_Mode_AIN;
	GPIO_InitStruct.GPIO_Pin=	GPIO_Pin_8;
	GPIO_Init(GPIOF,&GPIO_InitStruct);
	
	Adc3_Init( );
}
//��ȡLight Sens��ֵ
//0~100:0,�;100,���� 
u8 Lsens_Get_Val(void)
{
	u32 temp_val=0;
	u8 t;
	for(t=0;t<LSENS_READ_TIMES;t++)
	{
		temp_val+=Get_Adc3(LSENS_ADC_CHX);	//��ȡADCֵ
		delay_ms(5);
	}
	temp_val/=LSENS_READ_TIMES;//�õ�ƽ��ֵ 
	if(temp_val>4000)temp_val=4000;
	return (u8)(100-(temp_val/40));
}

void send_str(char *str)//�����ַ���
{
	while(*str != '\0')
	{
		USART_SendData(USART1,*str);//����һ���ֽڵ������Ժ�
		while( USART_GetFlagStatus(USART1,USART_FLAG_TC) == RESET);//�ȴ����ݷ������
		str++;
	}
}











