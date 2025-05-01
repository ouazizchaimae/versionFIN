import React from 'react';
import {KeyboardAvoidingView, Platform, SafeAreaView} from 'react-native';
import {globalStyle} from '../../../../../../shared/globalStyle';

import AnalyseChimiqueDetailAdminTabNavigation from '../../../../../../navigation/admin/expedition/AnalyseChimiqueDetailAdminTabNavigation';

const AnalyseChimiqueDetailAdmin = () => {
  return (
    <SafeAreaView style={globalStyle.myContainer}>

      <KeyboardAvoidingView
        behavior={Platform.OS === 'ios' ? 'padding' : 'height'}
        style={{ flex: 1 }} >
        <AnalyseChimiqueDetailAdminTabNavigation />
      </KeyboardAvoidingView>
    </SafeAreaView>
  );
};

export default AnalyseChimiqueDetailAdmin;
