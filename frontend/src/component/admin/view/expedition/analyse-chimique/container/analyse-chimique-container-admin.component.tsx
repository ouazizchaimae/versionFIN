import React from 'react';
import {KeyboardAvoidingView, Platform, SafeAreaView} from 'react-native';
import {globalStyle} from '../../../../../../shared/globalStyle';

import AnalyseChimiqueAdminTabNavigation from '../../../../../../navigation/admin/expedition/AnalyseChimiqueAdminTabNavigation';

const AnalyseChimiqueAdmin = () => {
  return (
    <SafeAreaView style={globalStyle.myContainer}>

      <KeyboardAvoidingView
        behavior={Platform.OS === 'ios' ? 'padding' : 'height'}
        style={{ flex: 1 }} >
        <AnalyseChimiqueAdminTabNavigation />
      </KeyboardAvoidingView>
    </SafeAreaView>
  );
};

export default AnalyseChimiqueAdmin;
