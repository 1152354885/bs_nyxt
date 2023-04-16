#include "adc.h"
#include "delay.h"
#include "sys.h"

 

//��ʼ��ADC3
//�������ǽ��Թ���ͨ��Ϊ��
//����Ĭ�Ͻ�����ͨ��6																	   
void  Adc3_Init(void)
{      
	ADC_InitTypeDef ADC_InitStructure;
	
	 //ʹ��ADC3ͨ��ʱ��
	RCC_APB2PeriphClockCmd(RCC_APB2Periph_ADC3,ENABLE);
  //��Ƶ   
	RCC_ADCCLKConfig(RCC_PCLK2_Div6);
	//��λADC3,������ ADC3��ȫ���Ĵ�������Ϊȱʡֵ
	ADC_DeInit(ADC3);
	//ADC����ģʽ: ����ģʽ
  ADC_InitStructure.ADC_Mode = ADC_Mode_Independent;
	//ģ��ת�������ڵ�ͨ��ģʽ
	ADC_InitStructure.ADC_ScanConvMode = DISABLE;
		//ģ��ת�������ڵ���ת��ģʽ
  ADC_InitStructure.ADC_ContinuousConvMode = DISABLE;
		//ת��������������ⲿ��������
	ADC_InitStructure.ADC_ExternalTrigConv = ADC_ExternalTrigConv_None;
	//ADC�����Ҷ���
	ADC_InitStructure.ADC_DataAlign = ADC_DataAlign_Right;
	//˳����й���ת����ADCͨ������Ŀ
	ADC_InitStructure.ADC_NbrOfChannel = 1;
			//����ADC_InitStruct��ָ���Ĳ�����ʼ������ADCx�ļĴ���  
	ADC_Init(ADC3,&ADC_InitStructure);
	//ʹ��ָ����ADC3
	ADC_Cmd(ADC3,ENABLE);
	//ʹ�ܸ�λУ׼  
	ADC_ResetCalibration(ADC3);
	//�ȴ���λУ׼����
	while(ADC_GetResetCalibrationStatus(ADC3));
//����У׼
	ADC_StartCalibration(ADC3);
		 //�ȴ�У׼����
	while(ADC_GetCalibrationStatus(ADC3));
}	

//���ADC3ĳ��ͨ����ֵ
//ch:ͨ��ֵ 0~16
//����ֵ:ת�����
u16 Get_Adc3(u8 ch)   
{
  //����ָ��ADC�Ĺ�����ͨ����һ�����У�����ʱ��
	//ADC3,ADCͨ��,����ʱ��Ϊ239.5����	  			    
	ADC_RegularChannelConfig(ADC3, ch, 1, ADC_SampleTime_239Cycles5 );
	//ʹ��ָ����ADC3�����ת����������	
	ADC_SoftwareStartConvCmd(ADC3,ENABLE);
	//�ȴ�ת������
	while(!ADC_GetFlagStatus(ADC3,ADC_FLAG_EOC));
	//�������һ��ADC3�������ת�����
	return ADC_GetConversionValue(ADC3);
} 
